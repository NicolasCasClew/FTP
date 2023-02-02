import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SubirFtp {
	static FTPClient cliente = new FTPClient();
	static String srvftp = "192.168.192.75";
	static String usuario = "pgv";
	static String password = "pgv";
	static String directorio = "/nicolas";

	public static void main(String[] args) throws IOException {


		File f = new File("elpepe.txt");
		f.createNewFile();
		//f.getName();
		//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		login();

		try {

			cliente.mkd(directorio);
			cliente.changeWorkingDirectory(directorio);
			cliente.setFileType(FTP.BINARY_FILE_TYPE);
			// Stream para subir archivos
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(f.getName()));
			cliente.storeFile(f.getName(), bis);
			bis.close();

			//cliente.deleteFile(f.getName());
			cliente.logout();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}


		cliente.disconnect();

	}
 static void login(){
	 try {
		 // Nos conectamos
		 System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		 cliente.connect(srvftp);
		 // Login
		 boolean login = cliente.login(usuario, password);
		 if (login) {
			 System.out.println("Conectado correctamente");
		 }else{
		System.out.println("Usuario/Clave Incorrecta");
		System.exit(0);
			 }
	 } catch (IOException ioe) {
		 ioe.printStackTrace();
	 }
}
}
