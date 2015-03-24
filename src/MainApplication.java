import com.pavel.bank.entity.Account;
import com.pavel.bank.parsing.sax.SAXBankParser;
import com.pavel.bank.parsing.stax.StAXBankParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Admin on 23.03.15.
 */
public class MainApplication {
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException{
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        List<Account> accounts = null;
        try {
            InputStream inputStream = new FileInputStream("src/Bank.xml");
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(inputStream);
            accounts = StAXBankParser.process(reader);
            System.out.println();
        }
        catch (XMLStreamException e){
            e.printStackTrace();
        }
        System.out.println();
    }
}
