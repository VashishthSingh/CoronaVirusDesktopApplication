package com.webscrapping;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebScrap {
	public static String getData(String c) throws IOException{
		Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/country/"+c+"/").get();  
        //String title = doc.title();  
        //System.out.println("title is: " + title);  
        //System.out.println(doc.body().html());  
        Elements elements= doc.select(".maincounter-number");
        //System.out.println(elements.html());
        String str1=elements.select("span").text();
        String str[]=str1.split(" ");
        StringBuffer sbf=new StringBuffer();
        sbf.append("<html>"
        		+ "<body style='text-align:center;color:green;'>");
        sbf.append("Confirmed Cases : "+str[0]+"<br> Dead Cases : "+str[1]+"<br> Recovered Cases : "+str[2]);
        sbf.append("</body>"
        		+ "</html>");
        return(sbf.toString());
	}
	public static void main(String args[]) throws IOException{
        JFrame jf=new JFrame("Crona Virus Cases");
        jf.setSize(250,300);
        JTextField jtf=new JTextField();
        jtf.setBounds(0, 0, 250, 35);
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel jl=new JLabel();
        jl.setBounds(0, 40, 250, 150);
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        JButton b=new JButton("Get");
        b.setBounds(0, 236, 250, 35);
        b.addActionListener((event)->{
        	String country=jtf.getText();
        	try {
				String str=getData(country);
				jl.setText(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
        });
        jf.add(jtf);
        jf.add(jl);
        jf.add(b);
        jf.setLayout(null);  
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocation(200,100);
	}
}
