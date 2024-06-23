import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerMinMaxScaler;

public class DataLoader {

    public static DataSetIterator loadTextData(String filePath) {
       
    }

    public static DataSetIterator loadImageData(String directoryPath) {
        // carregar e preparar dados de imagem
        // carregar imagens de um diretório  para DataSet
    }

    public static void normalizeData(DataSetIterator data) {
        DataNormalization normalizer = new NormalizerMinMaxScaler(0, 1);
        normalizer.fit(data);
        data.setPreProcessor(normalizer);
    }
}
