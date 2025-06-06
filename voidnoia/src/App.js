import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ChatPage from './pages/ChatPage';
import TrainPage from './pages/TrainPage';

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ChatPage />} />
        <Route path="/train" element={<TrainPage />} />
      </Routes>
    </Router>
  );
}