public class Main {

    public static void main(String[] args) {
        SimpleAI ai = new SimpleAI();

        // Carregar dados de texto
        DataSetIterator textData = DataLoader.loadTextData("path/to/text/data");
        DataLoader.normalizeData(textData);
        ai.train(textData);

        // Carregar dados de imagem
        DataSetIterator imageData = DataLoader.loadImageData("path/to/image/data");
        DataLoader.normalizeData(imageData);
        ai.train(imageData);

        // Predição
        int[] predictions = ai.predict(imageData.next());
        System.out.println("Predictions: " + Arrays.toString(predictions));
    }
}
