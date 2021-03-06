package br.com.pranuncio.util;
  
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
		this.host = "192.168.1.100";
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
		if (ftpClient.storeFile(arquivoFTP, uploadedFile.getInputstream())) { 
			return "Arquivo: " + arquivoFTP + " salvo com Sucesso";
		} else { 
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
