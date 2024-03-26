package outside;

import com.kaisikk.java.springlearning.service.ScalaService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Clojure {

    public String learnMe() {
        return "(def memoized-expensive-work (memoize yourwork))";
    }

}
