package com.suscalculator.gui;

import java.awt.dnd.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Sus implements MouseListener, DropTargetListener

{
	
	boolean selecionado;
	
	
	
/*-----------------------CRIA E ESTILIZA A JANELA DO PROGRAMA ----------------------------------//
    /**
     * @author Shayene
     */
	public  void frame() 
	{
      
		
		ImageIcon image = new ImageIcon("logo.png");//criando imagem de icone 
		ImageIcon imagem = new ImageIcon("csv.png"); //cria  o icone para o frame



		JPanel retangulo = new JPanel();
		retangulo.setBackground(new Color (0xD3D3D3)); 
		retangulo.setBounds(90, 200, 700, 150); //setando o tamanho do retangulo ///mudando a  cor de fundo do retangulo


		JLabel label = new JLabel(); //cria a label
		JLabel textoCima = new JLabel();


		JFrame frame = new JFrame(); //cria o frame
		
		frame.setTitle("Escala de Usabilidade do Sistema (SUS)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fecha a aplicaÃ§Ã£o
		frame.setResizable(false);//nÃ£o permite que a janela seja redimensionada pelo usuÃ¡rio
		frame.setSize(900, 600);//define a largura e altura da janela


		frame.getContentPane().setBackground(new Color(0xe9e9e9));//muda a cor de fundo do frame
		frame.setLocationRelativeTo(null);//posicionando o frame no meio da pÃ¡gina


		frame.setIconImage(image.getImage()); //muda o icone do frame

		label.setIcon(imagem);
		imagem.setImage(imagem.getImage().getScaledInstance(80, 80, 320));//redimensionando o tamanho da imagem passando como parametro ,largura ,altura e tamanho final


		label.setHorizontalTextPosition(JLabel.CENTER);//posicionando o texto no centro
		label.setVerticalTextPosition(JLabel.BOTTOM);//posicionando o texto embaixos
		label.setHorizontalAlignment(SwingConstants.CENTER);//posicionando o texto no centro absoluto do frame
		label.setVerticalAlignment(SwingConstants.CENTER); //posicionando o texto no centro absoluto do frame
		label.setFont(new Font("Roboto",Font.BOLD,22)); //mudando a fonte para negrito
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));//colocando o cursor em forma de mÃ£ozinha na label

		frame.setVisible(true);//ativa a visibilidade 
		retangulo.add(label); //adicionaindo a label ao retangulo
		frame.add(retangulo);
		frame.add(label);
		frame.add(textoCima);


		// Widget: Esse JLabel funciona como um botão; Ao ser clicado, o usuário poderá escolher um arquivo com a extensão .csv 
		// Container: Este container guarda o botão que realiza a função de busca de arquivo, bem como o ícone que indica os limites deste botão;
		
		retangulo.setComponentZOrder(label, 0); //priorizando o label ao inves do jpanel
		label.setText("Selecione seu arquivo .csv");//injetando texto no frame
		
		//Evento: Se esta JLabel receber um clique com o botão esquerdo do mouse, ativará o evento relacionado.
		
		label.addMouseListener(this); /*sÃ³ vai permitir  que o evento aconteÃ§a caso clique em um local especifico)*/
		
		textoCima.setText("Escala de usabilidade do sistema");
		textoCima.setVerticalTextPosition(JLabel.TOP);
		textoCima.setHorizontalAlignment(SwingConstants.CENTER);//posicionando o texto no centro absoluto do frame
		textoCima.setVerticalAlignment(SwingConstants.TOP); //posicionando o texto no centro absoluto do frame
		textoCima.setFont(new Font("Roboto",Font.BOLD,45)); //mudando a fonte para negrito
		
		//Evento: Arrastar um arquivo do File Explorer para qualquer parte do programa;
		
		//Tratamento de evento: O programa analisa se o arquivo fornecido possui a extensão .csv;
		//Caso possua, executará as funções do programa normalmente, e caso não, apresentará uma mensagem de erro.
		new  FileDrop( frame, new FileDrop.Listener()
		  {   public void  filesDropped( java.io.File[] files )
		      {   
				
			  int respostaSelecionador = 0;
			  
			 for(File f : files) {
				 if(f.toString().contains(".csv")==true) {
					 selecionado =true;
					 File selectedFile = new File(f.getAbsolutePath());
					 mostraResultadoFrame(label, selectedFile);
					 respostaSelecionador = 1;
					 break;
				 };
			 };
			 

				if (respostaSelecionador != 1)
				{
					respostaSelecionador = 0;
		            selecionado = false;
					//aqui podemos colocar um menu popup ou um joption pane de warning*/
					JOptionPane.showMessageDialog(null, "Arquivo selecionado não possui extensão .csv!!!", "Erro", JOptionPane.ERROR_MESSAGE);

				}
				
				
				
		      }   // end filesDropped
		  }); // end FileDrop.Listener
		
		 new  FileDrop( label, new FileDrop.Listener()
		  {   public void  filesDropped( java.io.File[] files )
		      {   
				
			  int respostaSelecionador = 0;
			  
			 for(File f : files) {
				 if(f.toString().contains(".csv")==true) {
					 selecionado =true;
					 File selectedFile = new File(f.getAbsolutePath());
					 mostraResultadoFrame(label, selectedFile);
					 respostaSelecionador = 1;
					 break;
				 };
			 };
			 

				if (respostaSelecionador != 1)
				{
					respostaSelecionador = 0;
		            selecionado = false;
					//aqui podemos colocar um menu popup ou um joption pane de warning*/
					JOptionPane.showMessageDialog(null, "Arquivo selecionado não possui extensão .csv!!!", "Erro", JOptionPane.ERROR_MESSAGE);

				}
		      }   // end filesDropped
		  }); // end FileDrop.Listener
		
	}

//-----------------------ATIVA O EVENTO QUANDO O MOUSE FOR CLICADO------------------------//
	/**@author Shayene
	 * 
	 */

	/*metodo que sÃ³ permite a abertura do filechooser quando clicar na label)*/
	
	//Tratamento de evento: Após perceber o clique do botão esquerdo do mouse na área delimitada, será tratado o evento do clique
	//com a invocação do FileChooser;
	@Override
	public void mouseClicked(MouseEvent e)
	{
		/*labels*/
		JLabel label = new JLabel(); //cria a label

		/*criando o selecionador de arquivos */
		JFileChooser  selecionador = new JFileChooser();


		
		
		/*ARRUMAR POIS  O JFILECHOOSER ESTA ABRINDO AUTOMATICAMENTE*/

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Selecione Apenas arquivos .csv", "csv");/*CRIANDO FILTRO .csv*/
		selecionador.setAcceptAllFileFilterUsed(false);	/*nÃ£o aceitar outros arquivos */
		selecionador.addChoosableFileFilter(filtro);	/*adicionando o filtro*/

		selecionador.setFileSelectionMode(JFileChooser.FILES_ONLY);/*seleciona apenas arquivos */ 
		int respostaSelecionador = selecionador.showOpenDialog(label);/*a caixa de dialogo vai abrir quando clicares na label,ARRUMAR*/

		if (respostaSelecionador == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = selecionador.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());//pegando o caminho absoluto*/
            selecionado =true;
            mostraResultadoFrame(label, selectedFile);

		}else
		{
            selecionado = false;
			//aqui podemos colocar um menu popup ou um joption pane de warning*/
		//	JOptionPane.showMessageDialog(selecionador, "Nenhum arquivo Selecionado!!!", "Erro", JOptionPane.ERROR_MESSAGE);

		}
	}
	//----------------MOSTRA RESULTADO ----------------------//
	
	/**@author Shayene
	 * @param label
	 * @param arquivo
	 */
	
	//Callback: Essa função é chamada após a realização dos cálculos utilizando os valores do arquivo .csv;
	
	public void mostraResultadoFrame(JLabel label,File arquivo)
	{		
		JFrame resultado = new JFrame();
		JLabel  telaFinal = new JLabel();
		
		ImageIcon image = new ImageIcon("regua.png");//criando imagem de icone 
		
		
		double calculoFinal;
		
		calculoFinal = calcula(selecionado, arquivo);
		
		if(calculoFinal==-1) {
			selecionado = false;
			JOptionPane.showMessageDialog(null, "Arquivo Vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		};
		if(calculoFinal==-2) {
			selecionado = false;
			JOptionPane.showMessageDialog(null,"Arquivo possui dados inválidos!!!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		};
		
		resultado.setTitle("Escala de Usabilidade do Sistema (SUS)-RESULTADO");
		resultado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //fecha a aplicação
		resultado.setResizable(false);//nÃ£o permite que a janela seja redimensionada pelo usuÃ¡rio
		resultado.setSize(900, 600);//define a largura e altura da janela
		resultado.getContentPane().setBackground(new Color(0xe9e9e9));//muda a cor de fundo do frame
		resultado.setLocationRelativeTo(null);//posicionando o frame no meio da pÃ¡gina
		resultado.setVisible(true);//ativa a visibilidade
	    		
	   
	   				
		
		 resultado.add(telaFinal);
		 String valorFinal = String.format("%1.2f", calculoFinal);
			
		
		 System.out.println(calculoFinal);
		String status = status(calculoFinal,resultado);
		String formatoString =formatadaString(valorFinal,status);
		
	 
		telaFinal.setText(formatoString);
		telaFinal.setVerticalTextPosition(JLabel.TOP);
		telaFinal.setHorizontalAlignment(SwingConstants.CENTER);//posicionando o texto no centro absoluto do frame
		telaFinal.setVerticalAlignment(SwingConstants.CENTER); //posicionando o texto no centro absoluto do frame
		telaFinal.setFont(new Font("Roboto",Font.BOLD,110)); //mudando a fonte para negrito
	
	}
//-----------------CALCULA O RESULTADO DO SUS-------------//
	
	
	/**@author Ruan Almeida
	 * 
	 * @param selecionado 
	 * @param arquivo
	 * @return
	 */
	//Callback: Essa função é chamada após a realização dos cálculos utilizando os valores do arquivo .csv;
	@SuppressWarnings("resource")
	private double calcula(boolean selecionado,File arquivo)
	{
		double valorSus = 0;
		int numIteracoes = 0;

		if(selecionado==true) {
			
			try {
				Scanner inputStream = new Scanner(arquivo);
				
				if(inputStream.hasNext()==false)
					return -1;
				
			
				while(inputStream.hasNext()) {
					String data = inputStream.next();
				
					int result = 0;
					
					if(data.contains(":")==true ) {
						String[] vetor = data.split(",", 0);
						for(int i = 1;i<11;i++) {
							int num = Integer.parseInt(vetor[i]);
							
							if(num<0 || num > 5)
								return -2;
							
							if(i%2 == 0) 
								result += (5-num);
							
							else 
								result += (num - 1);
							
						};
						
						valorSus += result*2.5;
						numIteracoes++;
					}else{
						if((data.contains("1,")==true)||
								(data.contains("2,")==true||
								(data.contains("3,")==true)||
								(data.contains("4,")==true)||
								(data.contains("5,")==true))) {
							String[] vetor = data.split(",", 0);
							for(int i = 1;i<10;i++) {
								int num = Integer.parseInt(vetor[i]);
								
								if(num<0 || num > 5)
									return -2;
								
								if(i%2 == 0) 
									result += (5-num);
								
								else 
									result += (num - 1);
								
							};
							
							valorSus += result*2.5;
							numIteracoes++;
							
						};
						
					};
				};
					
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		}
		
		return valorSus/numIteracoes;


	}
	

	
//---------------------- INFORMA O STATUS DO RESULTADO------------------------//
	
       public static String status(double x,JFrame frame)
       {
    	
   		 String status = null ;
   		
    	   if (x <50)
    	   {
    		   status = "Inaceitável";
    		   frame.getContentPane().setBackground(Color.red);
    	   }
    	     
    		if ((x >= 50)  && (x <= 70)) 
    			
    		{
    		    status = "Marginal";
    		    frame.getContentPane().setBackground(Color.yellow);
    		    
    		}
    		if (x >= 70)
    		{
    			status = "Aceitável";
    			frame.getContentPane().setBackground(Color.green);
    		}
    		
    		
    		return status;
    			
    		   
    	   }
//-----------------------------FORMATA STRING---------------------------------//      
       public static String formatadaString(String um, String dois)
       {
    	    String formatoString=String.format("%s  %s",um,dois);
    	   
    	   
    	   return formatoString;
       } 

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
/*Eventos de Drag n drop*/
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		
	}




}
