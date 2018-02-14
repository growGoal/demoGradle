import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadFromFile{

    public ArrayList<String> getWords(String fileName){
        ArrayList<String> setWords = new ArrayList<String>();
        JSONParser parser = new JSONParser();
        try {
            System.out.println("src/test/resources/searchwords/" + fileName);
            String pathToFile  = "src/test/resources/searchwords/" + fileName;
            Object obj = parser.parse(new FileReader(pathToFile));

            JSONObject jsonObject = (JSONObject) obj;

            //String search = (String) jsonObject.get("Search");
            //String author = (String) jsonObject.get("Author");
            JSONArray companyList = (JSONArray) jsonObject.get("Search");

            Iterator<String> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                //System.out.println(iterator.next());
                setWords.add(iterator.next());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(setWords.size());
        return setWords;

    }
}