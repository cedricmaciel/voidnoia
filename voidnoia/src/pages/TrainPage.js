import { useState } from 'react';
import { Box, TextField, Button, Typography, Paper } from '@mui/material';
import Layout from '../components/Layout';

export default function TrainPage() {
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');

  const handleSubmit = () => {
    // Aqui depois vamos integrar com o backend
    alert(`Você me ensinou:\nPergunta: ${question}\nResposta: ${answer}`);
    setQuestion('');
    setAnswer('');
  };

  return (
    <Layout>
      <Typography variant="h4" gutterBottom sx={{ color: '#2E3B55' }}>
        Treinar a IA
      </Typography>
      <Paper elevation={3} sx={{ padding: 3 }}>
        <Typography variant="body1" paragraph>
          Aqui você pode ensinar novas respostas para a IA. Digite uma pergunta e a resposta correta.
        </Typography>
        
        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 3 }}>
          <TextField
            label="Pergunta que a IA não soube responder"
            variant="outlined"
            fullWidth
            multiline
            rows={3}
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
          />
          
          <TextField
            label="Resposta correta"
            variant="outlined"
            fullWidth
            multiline
            rows={4}
            value={answer}
            onChange={(e) => setAnswer(e.target.value)}
          />
          
          <Button
            variant="contained"
            color="primary"
            size="large"
            onClick={handleSubmit}
            disabled={!question || !answer}
            sx={{ 
              alignSelf: 'flex-end',
              padding: '10px 24px'
            }}
          >
            Ensinar IA
          </Button>
        </Box>
      </Paper>
    </Layout>
  );
}