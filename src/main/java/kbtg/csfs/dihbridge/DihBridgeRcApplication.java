package kbtg.csfs.dihbridge;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import kbtg.csfs.dihbridge.entity.VcRcCd;
import kbtg.csfs.dihbridge.repository.VcRcCdRepository;


@SpringBootApplication
public class DihBridgeRcApplication {
	
	private static final String END_WITH_ERROR_ABSOLUTE_PATH_INCORRECT = "End with Error [Absolute path incorrect]!!";

	enum Param  { ALL,PATH,TODATE }
	private final static String PATH_DEFAULT = "VcRcCd.json";
	private final static Param TYPE_DEFAULT = Param.ALL;
	
		
	
	
	public static void main(String[] args) {
		SpringApplication.run(DihBridgeRcApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner initialData(VcRcCdRepository repository) {
	    return (args)->{
	    	Map<String,Param> options =new HashMap<>(); 		    
	    	options.put("-p", Param.PATH);
	    	options.put("-t",Param.TODATE);
	    	options.put("-a",Param.ALL);
	    		    	
		    
		    String absolutePath = PATH_DEFAULT; 
		    Param type = TYPE_DEFAULT;
		    
		    for(int i=0; i < args.length; i++ ) {
		    	Param val = options.get(args[i]);
		    	if(val==Param.PATH) {	    		
		    		if(i+1 >= args.length) {
		    			System.out.println(END_WITH_ERROR_ABSOLUTE_PATH_INCORRECT);
		    			//System.exit(1);
		    			return;
		    		}
		    		absolutePath = args[i+1];
		    		if(absolutePath == null) {
		    			System.out.println(END_WITH_ERROR_ABSOLUTE_PATH_INCORRECT);
		    			//System.exit(1);
		    			return;
		    		}
		    	}
		    	if(val==Param.ALL || val == Param.TODATE) {
		    		type = val;
		    	}
		    }
	    	
		    List<VcRcCd> results= new ArrayList<>();
		    if(type==Param.ALL)
		    	results = repository.findByMaxLastUpdate();
		    else if(type==Param.TODATE)
		    	results = repository.findByCurrentdate();
			
			// Create ObjectMapper
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    
		    // Convert object to JSON string
		    String json=null;
			try {
				json = mapper.writeValueAsString(results);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		    System.out.println(json);
		    
		    
		    
		    
		    // Save JSON string to file
		    try(FileOutputStream fileOutputStream = new FileOutputStream(absolutePath)){
		    	mapper.writeValue(fileOutputStream, results);
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	System.out.println(END_WITH_ERROR_ABSOLUTE_PATH_INCORRECT);
		    	return;
		    }
		    return;
	    };
		
	}

}
