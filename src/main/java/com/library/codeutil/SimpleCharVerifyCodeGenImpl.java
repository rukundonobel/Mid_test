package com.library.codeutil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class SimpleCharVerifyCodeGenImpl implements IVerifyCodeGen{

    private static final Logger logger = LoggerFactory.getLogger(SimpleCharVerifyCodeGenImpl.class);

    private static final String[] FONT_TYPES = {"Arial", "Courier", "Georgia", "Times New Roman", "Verdana"};

    private static final int VALICATE_CODE_LENGTH = 4;


    private static void fillBackground(Graphics graphics, int width, int height) {

        graphics.setColor(Color.WHITE);

        graphics.fillRect(0, 0, width, height);


        for (int i = 0; i < 8; i++) {

            graphics.setColor(RandomUtils.randomColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.drawLine(x, y, x1, y1);
        }
    }


    @Override
    public String generate(int width, int height, OutputStream os) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillBackground(graphics, width, height);
        String randomStr = RandomUtils.randomString(VALICATE_CODE_LENGTH);
        createCharacter(graphics, randomStr);
        graphics.dispose();

        ImageIO.write(image, "JPEG", os);
        return randomStr;
    }


    @Override
    public VerifyCode generate(int width, int height) {
        VerifyCode verifyCode = null;
        try (

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            String code = generate(width, height, baos);
            verifyCode = new VerifyCode();
            verifyCode.setCode(code);
            verifyCode.setImgBytes(baos.toByteArray());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            verifyCode = null;
        }
        return verifyCode;
    }

    private void createCharacter(Graphics graph, String randomStr) {
        char[] charArray = randomStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {

            graph.setColor(new Color(50 + RandomUtils.nextInt(100),
                    50 + RandomUtils.nextInt(100), 50 + RandomUtils.nextInt(100)));

            graph.setFont(new Font(FONT_TYPES[RandomUtils.nextInt(FONT_TYPES.length)], Font.PLAIN, 26));

            graph.drawString(String.valueOf(charArray[i]), 15 * i + 5, 19 + RandomUtils.nextInt(8));
        }
    }
}

