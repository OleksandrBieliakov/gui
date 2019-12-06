package werner.e07.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class IterFileWrapper implements Iterable<String>, Iterator<String> {

    private BufferedReader br;
    private String nextLine;

    IterFileWrapper(String filename) {
        try {
            br = Files.newBufferedReader(Paths.get(filename), UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Iterator<String> iterator() {
        return this;
    }

    public boolean hasNext() {
        if(nextLine != null) return true;
        try {
            if((nextLine = br.readLine()) != null) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String next() throws NoSuchElementException {
        if(nextLine == null) throw new NoSuchElementException();
        String tmp = nextLine;
        nextLine = null;
        return tmp;
    }

}
