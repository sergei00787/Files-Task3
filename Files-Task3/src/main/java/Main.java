import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        openZip("/home/serg/IdeaProjects/Netology/Files-Task1/Games/savegames/save.zip",
                "/home/serg/IdeaProjects/Netology/Files-Task1/Games/savegames/");

        GameProgress gameProgress = openProgress("/home/serg/IdeaProjects/Netology/Files-Task1/Games/savegames/save2.dat");
        System.out.println(gameProgress);
    }

    public static void openZip(String pathZipFile, String pathOutput) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathZipFile))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                try (FileOutputStream fileOutputStream =
                             new FileOutputStream(pathOutput + "/" + zipEntry.getName())) {
                    for (int c = zis.read(); c != -1; c = zis.read()) {
                        fileOutputStream.write(c);
                    }
                    fileOutputStream.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static GameProgress openProgress(String fileSaveGame) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileSaveGame))) {
            GameProgress gameProgress1 = (GameProgress) ois.readObject();
            return gameProgress1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
