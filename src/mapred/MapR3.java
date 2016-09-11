package mapred;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapR3 extends Mapper<LongWritable, Text, Text,IntWritable> {

	@Override
	protected void map(LongWritable key, Text value,Context context)
					throws IOException, InterruptedException {
		//Noticias del dataset entregado 
		String noticia = StringEscapeUtils.unescapeHtml(value.toString());
		//Me limpia los datos y los guarda en noticia
		//cuantas noticias son publicadas en cada mes del año del 1987 y
		//encuentre cuantos titulos tienen longitudes de 1-10 palabra 


		Pattern p = Pattern.compile("<DATE>(\\s*)(?:\\d{1,2}-([A-Z]{3})-(\\d{4}))(.*)</DATE>");
		Matcher m = p.matcher(noticia);
		if (m.find()){
			 			String fechaCadena= m.group(2);//fecha de cada noticia
			 			Pattern pt = Pattern.compile("<TITLE>(.*)</TITLE>",Pattern.DOTALL);
			 			Matcher mt = pt.matcher(noticia.toString());
			 			if(mt.find()){
			 				String titulo= mt.group(1);
			 				if((titulo.split("\\s+").length>1)&&(titulo.split("\\s+").length<10)){
			 					IntWritable i= new IntWritable(1);
			 					Text t = new Text(fechaCadena);
			 					context.write(t, i);
			
			
						/*
			try {
				date = sdf0.parse(fechaCadena);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				if (year==1987){
					String mes= new DateFormatSymbols().getMonths()[month-1];
					//INICIO
					Pattern pt = Pattern.compile("<TITLE>(.*)</TITLE>",Pattern.DOTALL);
					Matcher mt = pt.matcher(noticia.toString());
					if(mt.find()){
						String titulo= mt.group(1);
						if((titulo.split("\\s+").length>1)&&(titulo.split("\\s+").length<10)){
							int numti=1;
							
							IntWritable i= new IntWritable(numti);
							Text t = new Text(mes);
							context.write(t, i);
						}
					}										
					//FIN		
							
					
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}
		}
	}
	}
}


