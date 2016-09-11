package mapred;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapR2 extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			Context context)
					throws IOException, InterruptedException {
		String noticia = StringEscapeUtils.unescapeHtml(value.toString());
		//Me limpia los datos y los guarda en line
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		
		Pattern p = Pattern.compile("<DATE>(\\s*)(\\d{1,2}-([A-Z]{3})-(\\d{4}))(.*)</DATE>");
		Matcher m = p.matcher(noticia);
		
		try {
			Date f_inicio= sdf.parse("16-02-1987");
			Date f_fin= sdf.parse("30-06-1987");	

			if(m.find()){
				String fechaCadena= m.group(2);
				SimpleDateFormat sdf1= new SimpleDateFormat("dd-MMM-yyyy");
				try {
					Date date = sdf1.parse(fechaCadena);
					System.out.println(date.toString());
					
					if(date.after(f_inicio) && date.before(f_fin)){
						String word="james-baker";
						int numeveces=noticia.split(word).length-1;
						context.write(new Text(word), new IntWritable(numeveces));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

