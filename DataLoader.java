import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerMinMaxScaler;

public class DataLoader {

    public static DataSetIterator loadTextData(String filePath) {
        // Implementação para carregar e preparar dados de texto
        // Exemplo: leitura de arquivos CSV, tokenização de texto, etc.
    }

    public static DataSetIterator loadImageData(String directoryPath) {
        // Implementação para carregar e preparar dados de imagem
        // Exemplo: carregar imagens de um diretório e convertê-las para DataSet
    }

    public static void normalizeData(DataSetIterator data) {
        DataNormalization normalizer = new NormalizerMinMaxScaler(0, 1);
        normalizer.fit(data);
        data.setPreProcessor(normalizer);
    }
}
