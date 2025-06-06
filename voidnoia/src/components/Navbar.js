import { AppBar, Toolbar, Button } from '@mui/material';
import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <AppBar position="static" sx={{ backgroundColor: '#2E3B55' }}>
      <Toolbar>
        <Button 
          color="inherit" 
          component={Link} 
          to="/"
          sx={{ 
            marginRight: 2,
            fontWeight: 'bold',
            fontSize: '1rem'
          }}
        >
          Chat
        </Button>
        <Button 
          color="inherit" 
          component={Link} 
          to="/train"
          sx={{ 
            fontWeight: 'bold',
            fontSize: '1rem'
          }}
        >
          Treinar
        </Button>
      </Toolbar>
    </AppBar>
  );
}