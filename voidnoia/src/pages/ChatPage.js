import { useState } from 'react';
import { Box, TextField, Button, Paper, Avatar } from '@mui/material';
import SendIcon from '@mui/icons-material/Send';
import Layout from '../components/Layout';

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
      <Paper 
        elevation={3} 
        sx={{ 
          height: '70vh', 
          padding: 2, 
          display: 'flex', 
          flexDirection: 'column',
          backgroundColor: '#f5f5f5'
        }}
      >
        <Box sx={{ flex: 1, overflow: 'auto', mb: 2 }}>
          {messages.map((msg, i) => (
            <Box 
              key={i} 
              sx={{ 
                display: 'flex', 
                justifyContent: msg.isUser ? 'flex-end' : 'flex-start',
                mb: 2
              }}
            >
              <Box
                sx={{
                  display: 'flex',
                  alignItems: 'center',
                  flexDirection: msg.isUser ? 'row-reverse' : 'row',
                  gap: 1
                }}
              >
                <Avatar sx={{ 
                  bgcolor: msg.isBot ? '#4CAF50' : '#2196F3',
                  width: 32, 
                  height: 32 
                }}>
                  {msg.isBot ? 'V' : 'U'}
                </Avatar>
                <Paper
                  sx={{
                    padding: 1.5,
                    maxWidth: '70%',
                    backgroundColor: msg.isBot ? '#E8F5E9' : '#E3F2FD',
                    borderRadius: msg.isUser ? 
                      '18px 18px 0 18px' : '18px 18px 18px 0'
                  }}
                >
                  {msg.text}
                </Paper>
              </Box>
            </Box>
          ))}
        </Box>
        
        <Box sx={{ display: 'flex', gap: 1 }}>
          <TextField
            fullWidth
            variant="outlined"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyPress={(e) => e.key === 'Enter' && handleSend()}
            placeholder="Digite sua mensagem..."
            sx={{ 
              backgroundColor: 'white',
              borderRadius: '4px'
            }}
          />
          <Button
            variant="contained"
            color="primary"
            onClick={handleSend}
            sx={{ 
              minWidth: '56px',
              height: '56px'
            }}
          >
            <SendIcon />
          </Button>
        </Box>
      </Paper>
    </Layout>
  );
}