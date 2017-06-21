import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class Producer5 {

	public static void main(String[] args) throws Exception{

//      // Check arguments length value
//      if(args.length == 0){
//          System.out.println("Enter topic name");
//          return;
//      }

      

      // create instance for properties to access producer configs
      Properties props = new Properties();

      //Assign localhost id
      props.put("bootstrap.servers", "localhost:9092");


      props.put("key.serializer",
              "org.apache.kafka.common.serialization.StringSerializer");

      props.put("value.serializer",
              "org.apache.kafka.common.serialization.StringSerializer");

      Producer<String, String> producer = new KafkaProducer<String, String>(props);

      long startTime = System.currentTimeMillis();
      
    //Assign topicName to string variable
      String topicName = "test";//args[0].toString();
      
      
      //reading data from file
      String path = "Data/twa06.csv";
      //end reading data from file
      List<Double> data = DataParser.ReadCSV(path);
      double[] da = new double[data.size()];
      for (int i = 0; i < da.length; i++){
          da[i] = data.get(i);
      }
      
      
      
      for(int i = 0; i < 10; i++)
      {
    	  JSONArray list = new JSONArray();
    	  for(int j=0;j<2500;j++)
    	  {
    	  //creating value in json
    	  JSONObject obj = new JSONObject();
    	  obj.put("RecordNum", new Integer(i));
    	  obj.put("DataType","BP");
    	  //obj.put("DataValueInt", new Integer(100));
    	  obj.put("DataValueDouble", da[(i*2500+j)%data.size()]);
    	  //obj.put("is_verified", new Boolean(true));
    	  list.add(obj);
    	  }
    	  
    	  String value=list.toJSONString();
    	  //end creating value in json
    	  ProducerRecord<String,String> record= new ProducerRecord<String, String>(topicName,"123",value);//123 is the key
    	  producer.send(record);
    	  
    	  Thread.sleep(5000);
    	  
      }
      
     // long endTime   = System.currentTimeMillis();
     // long totalTime = endTime - startTime;
     // System.out.println(totalTime);
      
    // System.out.println("Message sent successfully");
      producer.close();
  }
	
}
