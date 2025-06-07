import { useState } from 'react';
import Layout from '../components/Layout';
import './TrainPage.css';

export default function TrainPage() {
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');

  
  const handleSubmit = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/train', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          question: question,
          answer: answer
        })
      });
  
      if (response.ok) {
        alert('Conhecimento adicionado com sucesso!');
        setQuestion('');
        setAnswer('');
      } else {
        throw new Error('Falha ao treinar a IA');
      }
    } catch (error) {
      alert(`Erro: ${error.message}`);
    }
  };
  return (
    <Layout>
      <div className="train-container">
        <div className="div-title">
        <h1 className="train-title">Treinar a IA</h1>
       </div>
        <div className="train-paper">
          <p className="train-description">
            Aqui você pode ensinar novas respostas para a IA. Digite uma pergunta e a resposta correta.
          </p>
          
          <div className="train-form">
            <div className="input-group">
              <label htmlFor="question-input">Pergunta que a IA não soube responder</label>
              <textarea
                id="question-input"
                className="train-input"
                rows={3}
                value={question}
                onChange={(e) => setQuestion(e.target.value)}
                placeholder="Digite a pergunta..."
              />
            </div>
            
            <div className="input-group">
              <label htmlFor="answer-input">Resposta correta</label>
              <textarea
                id="answer-input"
                className="train-input"
                rows={4}
                value={answer}
                onChange={(e) => setAnswer(e.target.value)}
                placeholder="Digite a resposta correta..."
              />
            </div>
            
            <button
              className="train-button"
              onClick={handleSubmit}
              disabled={!question || !answer}
            >
              Ensinar IA
            </button>
          </div>
        </div>
      </div>
    </Layout>
  );
}