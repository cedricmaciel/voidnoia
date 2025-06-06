import { Container } from '@mui/material';
import Navbar from './Navbar';

export default function Layout({ children }) {
  return (
    <>
      <Navbar />
      <Container maxWidth="lg" sx={{ paddingTop: 4 }}>
        {children}
      </Container>
    </>
  );
}