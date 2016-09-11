package mapred;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapR1 extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
			String line = StringEscapeUtils.unescapeHtml(value.toString());
			//Me limpia los datos

		Pattern p = Pattern.compile("<TITLE>(.*)</TITLE>",Pattern.DOTALL);
		Matcher m = p.matcher(line.toString());
		if(m.find()){
			String title= m.group(1);
			if(title.split("\\s+").length>5){
				Pattern v = Pattern.compile("<BODY>(.*)</BODY>",Pattern.DOTALL);
				Matcher a = v.matcher(line.toString());
				if (a.find()){
					String body=a.group(1);
					IntWritable i= new IntWritable(body.split("\\s+").length);
					Text t = new Text(title);
					context.write(t, i);
					//LINEA NINEA
											}
				}
				}	
				
	}
}