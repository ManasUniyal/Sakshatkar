package Main;//Server is receiving file

import Constant.RequestFile;
import RequestClasses.Response;
import Utilities.SaveFile;
import Utilities.SqlQueryExecuter;

import java.io.*;
import java.net.Socket;

public class HandleClientFile extends Thread{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private int type;
    private String fileName;

    public HandleClientFile(Socket socket) {
        System.out.println("In the constructor of HandleClientFile");
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println("Data Input stream created at server");
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Object Output stream created at server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        System.out.println("In run method of File Client Handler");

        while(true) {


            try {

                fileName = dataInputStream.readUTF();

//                String extension = dataInputStream.readUTF();
//                System.out.println("Extension = " + extension);

                type = dataInputStream.readInt();


                System.out.println(type);
                System.out.println(fileName);

                Object response = (Object) process();

                objectOutputStream.writeObject(response);
                objectOutputStream.flush();

                System.out.println("Response sent");

            } catch (IOException e) {
//                e.printStackTrace();
            }

        }
    }

    private Object process() throws IOException {

        if(type == RequestFile.PROFILEPICTURE.ordinal()){
            return _saveProfilePicture();
        }

        return new Response(1,"Error in storing on server. Try again later.");
    }

    private Object _saveProfilePicture() throws IOException {

        String dirName = "src/ProfilePictures";
        String filePath = dirName + "/" + fileName;
        Boolean doesFileExist = new File(dirName).mkdir();
        try{
            new SaveFile(dataInputStream).saveFile(dirName, fileName);
        }
        catch(Exception e){
            return new Response(1, "Error in storing image on server");
        }

        String fileNameWithoutExtension = fileName.substring(0,fileName.indexOf("."));

        //SQL Query to update ImageLocation on Server
        Main.SQLQUERYEXECUTER.update("UPDATE user SET picture = " + "'" + filePath +"'" + " WHERE userID = "+ "'" + fileNameWithoutExtension + "';");

        return new Response(0,"");
    }

}