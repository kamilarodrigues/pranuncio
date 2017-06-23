package br.com.pranuncio.util;
 
import java.io.FileInputStream;
import java.io.IOException; 

import org.apache.commons.net.ftp.FTPClient;
import org.primefaces.model.UploadedFile;

public class Ftp {

	private FTPClient ftpClient;
	private String host;
	private String user;
	private String password;

	public Ftp() {
		ftpClient = new FTPClient();
		this.host = "systm.com.br";
		this.user = "tmftp";
		this.password = "20SimpleS78";
	}

	public boolean conectar() throws IOException {
		ftpClient.connect(host);
		ftpClient.login(user, password);
		if (ftpClient.isConnected()) {
			return true;
		} else
			return false;
	}

	public String enviarArquivo(UploadedFile uploadedFile, String arquivoFTP, String pasta) throws IOException {
		ftpClient.changeWorkingDirectory(pasta);
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		FileInputStream arqEnviar = (FileInputStream) uploadedFile.getInputstream();
		arquivoFTP = new String(arquivoFTP.getBytes("ISO-8859-1"), "UTF-8");
		if (ftpClient.storeFile(arquivoFTP, arqEnviar)) {
			arqEnviar.close();
			return "Arquivo: " + arquivoFTP + " salvo com Sucesso";
		} else {
			arqEnviar.close();
			return "Erro Salvar Arquivo";
		}
	}

	public String excluirArquivo(String arquivoFTP, String pasta) throws IOException {
		if (ftpClient.deleteFile(pasta + arquivoFTP)) {
			ftpClient.cwd(pasta);
			return "Excluido com sucesso";
		} else {
			return "Erro ao excluir";
		}

	}

	public void desconectar() throws IOException {
		ftpClient.logout();
		ftpClient.disconnect();
	}
}
