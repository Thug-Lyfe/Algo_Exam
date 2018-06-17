package directed;

import stuff.In;
import stuff.Queue;
import stuff.StdOut;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class webCrawler {
    public void crawlWeb(String sourceWebPage) {
        // Based on https://algs4.cs.princeton.edu/42digraph/WebCrawler.java.html
        // breath vs depth er 2 forskellige måder at læse et træ på
        // breath læser række for række
        // depth læser kolloner først (den læser den øverste, så den nedenunder til venstre, og dens venstre osv.)
        // se breath_vsdepth.png for visuelt ex.
        // se depth_first_illustration.png og breath_first_illustation for visuelt ex.
        // timeout connection after 500 milliseconds
        System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        System.setProperty("sun.net.client.defaultReadTimeout",    "1000");

        Queue<String> webPagesQueue = new Queue<>();
        webPagesQueue.enqueue(sourceWebPage);

        HashSet<String> visited = new HashSet<>();
        visited.add(sourceWebPage);

        while (!webPagesQueue.isEmpty()) {
            String currentWebPage = webPagesQueue.dequeue();

            String webPageContent;
            try {
                In in = new In(currentWebPage);
                webPageContent = in.readAll();
            } catch (IllegalArgumentException exception) {
                StdOut.println("Could not open " + currentWebPage);
                continue;
            }

            StdOut.println(currentWebPage + " crawled");

            // find links som http://xxx.yyy.zzz or https://xxx.yyy.zzz
            String regexp = "http(s?)://(\\w+\\.)+(\\w+)";
            Pattern pattern = Pattern.compile(regexp);

            Matcher matcher = pattern.matcher(webPageContent);

            // Find all matches
            while (matcher.find()) {
                String webPage = matcher.group();

                if (!visited.contains(webPage)) {
                    webPagesQueue.enqueue(webPage);
                    visited.add(webPage);
                }
            }
        }
    }

    public static void main(String[] args) {
        String sourceWebPage = "https://google.com";
        new webCrawler().crawlWeb(sourceWebPage);
    }
}
