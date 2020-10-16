package com.github.fengyuchenglun.apidoc.core.common.diff;

import com.google.common.base.Charsets;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.List;

/**
 * 文件对比工具
 *
 * @author duanledexianxianxian
 */
@Getter
public class FileMatcher {

    /**
     * The Match patcher.
     */
    MatchPatcher matchPatcher = new MatchPatcher();

    /**
     * 变化的点
     */
    private int changs;

    private List<MatchPatcher.Diff> diffs;

    /**
     * Compare int.
     *
     * @param template the template
     * @param build    the build
     * @return the int
     */
    public int compare(Path template, Path build) {
        return compare(readFile(template), readFile(build));
    }

    /**
     * Compare int.
     *
     * @param templateText the template text
     * @param buildText    the build text
     * @return the int
     */
    public int compare(String templateText, String buildText) {
        diffs = matchPatcher.diff_main(templateText, buildText, true);
        for (MatchPatcher.Diff diff : diffs) {
            if (!diff.operation.equals(MatchPatcher.Operation.EQUAL)) {
                changs++;
            }
        }
        return changs;
    }

    /**
     * Render html.
     *
     * @param templateHtml the template html
     * @param resultHtml   the result html
     */
    public void renderHtml(Path templateHtml, Path resultHtml) {
        String results = matchPatcher.diff_prettyHtml(diffs);
        String[] lines = br(results).replaceAll("<span>|</span>", "").split("\n");
        String html = readFile(templateHtml);
        html = html.replace("${content}", lines(lines));
        writeFile(resultHtml, html, Charsets.UTF_8);
        FileSystem.open(resultHtml);
    }

    private String lines(String[] lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            stringBuilder.append("<tr><td class=\"line-numbers\">").append(i)
                    .append("</td><td>")
                    .append(lines[i]).append("</td></tr>");
        }
        return stringBuilder.toString();
    }

    private static String br(String text) {
        return text.replaceAll("&para;", "");
    }


    /**
     * 读取文件内容
     *
     * @param path the path
     * @return the string
     */
    public static String readFile(Path path) {
        // Read a file from disk and return the text contents.
        StringBuilder sb = new StringBuilder();
        try (FileReader input = new FileReader(path.toFile());
             BufferedReader bufRead = new BufferedReader(input)) {
            String line = bufRead.readLine();
            while (line != null) {
                sb.append(line).append('\n');
                line = bufRead.readLine();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return sb.toString();
    }

    /**
     * Write file.
     *
     * @param file        the file
     * @param content     the content
     * @param charset     the charset
     * @param openOptions the open options
     */
    public void writeFile(Path file, String content, Charset charset, OpenOption... openOptions) {
        if (file.getParent() != null) {
            try {
                Files.createDirectories(file.getParent());
            } catch (IOException e) {
                throw new RuntimeException("Failed create directory", e);
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(file, charset, openOptions)) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file", e);
        }
    }

}
