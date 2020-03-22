import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Aplicacao extends Planilha{
	
	  public static void main(String[] argv)  throws Exception {
		  	livroDeOfertas();
			Object[] possibilities = run().toArray();
			Object[] operacaoList = {"Compra", "Venda"};
			int operacao = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "Bolsa de Valores", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, operacaoList, null);
			String acao = (String)JOptionPane.showInputDialog(
		            null,
		            "A��es",
		            "Bolsa de Valores",
		            JOptionPane.PLAIN_MESSAGE,
		            null,
		            possibilities,
		            possibilities[0]);
			int quant = Integer.parseInt((String) JOptionPane.showInputDialog(null,
			        "Digite a quantidade:",
			        "Bolsa de Valores", JOptionPane.INFORMATION_MESSAGE,
			        null,
			        null,
			        ""));
			double val = Double.parseDouble((String) JOptionPane.showInputDialog(null,
			        "Digite o valor:",
			        "Bolsa de Valores", JOptionPane.INFORMATION_MESSAGE,
			        null,
			        null,
			        ""));
			Corretora c = new Corretora();
			String a = c.Corretora(acao, quant, val, operacao==1?"Venda":"Compra");
			criarArquivo(a);
			livroDeOfertas();
	  }
	  
	  public static void livroDeOfertas() {
		   ArrayList livroOfertas = lerLivroOferta();
		    String saida = "";
		    saida = "<html>"
		            + "<table border = '1'>"
		            + "<tr><th>COD A��o</th><th>Quantidade</th><th>Valor</th><th>Opera��o</th></tr>";


		    for (int i = 0; i < livroOfertas.size(); i++) {
		    	String[] livroOfertasSplit = livroOfertas.get(i).toString().split(",");
		    	saida = saida + "<tr><td>" + livroOfertasSplit[0]+ "</td><td>" + livroOfertasSplit[1] + "</td><td>" + livroOfertasSplit[2] + "</td><td>"+ livroOfertasSplit[3] + "</td></tr>";
		    }

		    saida = saida + "</table></html>";
		   
		    JOptionPane.showMessageDialog(null,saida);
	  }
	  
	  public static void criarArquivo(String message) {
		  try {
		  	String[] messageSplit = message.split(",");
		  	File file = new File("LivroDeOfertas.csv");
	        FileWriter writer;
			
				writer = new FileWriter(file, true);
			
	        BufferedWriter bw = new BufferedWriter(writer);
	        String messageConcat = (messageSplit[2] + "," + messageSplit[0]+ "," + messageSplit[1]+ "," +messageSplit[3]);
	        bw.write(messageConcat);
	        bw.newLine();
	        bw.flush();
	        bw.close();
		  } catch (IOException e) {
				e.printStackTrace();
			}
	  }
	  
}
