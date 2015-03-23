import com.pavel.bank.parsing.sax.SAXBankParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 23.03.15.
 */
public class MainApplication {
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXBankParser handler = new SAXBankParser();
        parser.parse(new File("c:/Bank.xml"), handler);
        System.out.println();
    }
}
