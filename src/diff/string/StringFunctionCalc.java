package diff.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StringFunctionCalc {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine().trim();
		
		Map<String,Integer> map=new HashMap<String, Integer>();
		int length=s.length();
		for(int i=0;i<length;i++)
		{
			for(int j=i+1;j<=length;j++)
			{
				String ss=s.substring(i, j);
				if(map.containsKey(ss))
					map.put(ss, map.get(ss)+1);
				else
					map.put(ss, 1);
			}
		}
		
		int max=0;
		int cur;
		for(Entry e : map.entrySet())
		{
			String ss=(String) e.getKey();
			cur=(Integer)e.getValue()*(((String) e.getKey()).length());
			if(cur>max)
				max=cur;
		}
		
		System.out.println(max);
	}

}
