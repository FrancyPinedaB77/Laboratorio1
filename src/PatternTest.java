import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
	
	public PatternTest() {
		// TODO Auto-generated constructor stub
		String data="<DATE>26-FEB-1987 15:14:36.41</DATE>";
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		
		Pattern p = Pattern.compile("<DATE>(\\s*)(\\d{1,2}-([A-Z]{3})-(\\d{4}))(.*)</DATE>");
		Matcher m = p.matcher(data);
		
		try {
			Date f_inicio= sdf.parse("16-02-1987");
			Date f_fin= sdf.parse("30-06-1987");
			

			if(m.find()){
				String fechaCadena= m.group(2);
				System.out.println(fechaCadena);
				SimpleDateFormat sdf1= new SimpleDateFormat("dd-MMM-yyyy");
				try {
					Date date = sdf1.parse(fechaCadena);
					System.out.println(date.toString());
					if(date.after(f_inicio) && date.before(f_fin)){
						System.out.println("SI ESTA");
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
	
	public static void main(String[] args)  {
		
		new PatternTest();
	}
}
