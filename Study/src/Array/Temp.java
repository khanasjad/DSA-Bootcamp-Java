package Array;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    public class FileUtilTest {

        @Test
        public void testReadFileIntoArrayList() throws IOException {
            // Arrange
            String filePath = "mockFilePath";
            BufferedReader mockBufferedReader = mock(BufferedReader.class);
            MockedStatic<FileReader> mockedFileReaderStatic = mockStatic(FileReader.class);

            mockedFileReaderStatic.when(() -> new FileReader(filePath)).thenReturn(mock(FileReader.class));
            when(mockBufferedReader.readLine()).thenReturn("line1", "line2", null);

            try (MockedStatic<BufferedReader> mockedBufferedReaderStatic = mockStatic(BufferedReader.class)) {
                mockedBufferedReaderStatic.when(() -> new BufferedReader(any(FileReader.class))).thenReturn(mockBufferedReader);

                // Act
                List<String> result = FileUtil.readFileIntoArrayList(filePath);

                // Assert
                assertEquals(Arrays.asList("line1", "line2"), result);
                verify(mockBufferedReader, times(3)).readLine();
            }

            mockedFileReaderStatic.close();
        }
    }

    // Your original class that contains the method
    public class FileUtil {
        public static List<String> readFileIntoArrayList(String filePath) {
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return lines;
        }
    }




