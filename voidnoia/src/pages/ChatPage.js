import { useState } from 'react';
import Layout from '../components/Layout';
import './ChatPage.css';

export default function ChatPage() {
  const [messages, setMessages] = useState([
    { text: "Olá! Como posso te ajudar com programação hoje?", isBot: true }
  ]);
  const [input, setInput] = useState('');

  const handleSend = () => {
    if (input.trim() === '') return;
    
    // Adiciona mensagem do usuário
    setMessages([...messages, { text: input, isUser: true }]);
    setInput('');
    
    // Resposta simulada
    setTimeout(() => {
      setMessages(prev => [...prev, { 
        text: "Estou aprendendo ainda! Quando integrarmos o backend, poderei responder melhor.", 
        isBot: true 
      }]);
    }, 1000);
  };

  return (
    <Layout>
      <div className="chat-container">
        <div className="messages-container">
          {messages.map((msg, i) => (
            <div 
              key={i} 
              className={`message-wrapper ${msg.isUser ? 'user' : ''}`}
            >
              <div className={`message-content ${msg.isUser ? 'user' : ''}`}>
                <div className={`message-avatar ${msg.isBot ? 'bot' : ''}`}>
                  {msg.isBot ? 'V' : 'U'}
                </div>
                <div className={`message-bubble ${msg.isUser ? 'user' : ''}`}>
                  {msg.text}
                </div>
              </div>
            </div>
          ))}
        </div>
        
        <div className="input-area">
          <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyPress={(e) => e.key === 'Enter' && handleSend()}
            placeholder="Digite sua mensagem..."
            className="chat-input"
          />
          <button
            onClick={handleSend}
            className="send-button"
          >
            <svg width="24" height="24" viewBox="0 0 24 24" fill="currentColor">
              <path d="M2.01 21L23 12 2.01 3 2 10l15 2-15 2z"/>
            </svg>
          </button>
        </div>
      </div>
    </Layout>
  );
}