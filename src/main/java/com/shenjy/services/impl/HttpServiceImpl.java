package com.shenjy.services.impl;

import com.shenjy.services.HttpService;
import org.apache.catalina.connector.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/07/04
 */
@Service
public class HttpServiceImpl implements HttpService {


    @Override
    public void download(HttpServletRequest request, HttpServletResponse response,
                         String fileUrl) {
        File file = new File(fileUrl);

        long startByte = 0;
        long endByte = file.length() - 1;

        String range = request.getHeader("range");
        if (StringUtils.isNotBlank(range) && range.contains("bytes=") && range.contains("-")) {
            range = range.substring(range.lastIndexOf("=") + 1).trim();
            String[] ranges = range.split("-");
            try {
                if (1 == ranges.length) {
                    //类型一：bytes=-2343
                    if (range.startsWith("-")) {
                        endByte = Long.parseLong(ranges[0]);
                    }
                    //类型二：bytes=2343-
                    else if (range.endsWith("-")) {
                        startByte = Long.parseLong(ranges[0]);
                    }
                }
                //类型三：bytes=22-2343
                else if (2 == ranges.length) {
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }
            } catch (NumberFormatException e) {
                startByte = 0;
                endByte = file.length() - 1;
            }
        }

        long contentLength = endByte - startByte + 1;
        String fileName = file.getName();
        String contentType = request.getServletContext().getMimeType(fileName);

        response.setHeader("Accept-Ranges", "bytes");
        response.setStatus(Response.SC_PARTIAL_CONTENT);
        response.setContentType(contentType);
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Disposition", "inline;filename=" + fileName);
        response.setHeader("Content-Length", String.valueOf(contentLength));
        response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + file.length());

        BufferedOutputStream outputStream = null;
        RandomAccessFile randomAccessFile = null;

        long transmitted = 0;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startByte);
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
                Thread.sleep(10);
            }
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }

            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();
            System.out.println("下载完毕: " + startByte + "-" + endByte + ": " + transmitted);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
