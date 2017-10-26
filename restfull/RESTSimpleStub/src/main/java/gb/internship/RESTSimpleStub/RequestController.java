package gb.internship.RESTSimpleStub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Простой REST контроллер.
 *
 * @author Aleksandr Vvedensky
 */
@RestController
public class RequestController {

    private Log LOG = LogFactory.getLog(RequestController.class);
    public static final String DEFAULT_RESPONSE = "You see default string." +
            "<br>Use: http://localhost:8080/echo?echoString=myString" +
            "<br>to see your string.";

    /**
     * Обслуживает это-запросы.
     *
     * @param echoString строка аргумент из запроса.
     * @return полученный echoString или значение по умолчанию.
     */
    @RequestMapping("/echo")
    public String echo(
            @RequestParam(value = "echoString", defaultValue = DEFAULT_RESPONSE) String echoString) {
        return echoString;
    }


    /**
     * Выводит сообщения в лог, если if(true)
     *
     * @param msg сообщения для лога.
     */
    private void printToLog(String msg) {
        if (true) {
            LOG.info(msg);
        }
    }
}
