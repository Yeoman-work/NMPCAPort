import './App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import LoginView from "./views/LoginView";

function App() {
  return (
    <div className="App">
        <Router>
            <Routes>
                <Route path={'/yeoman'}>
                    <Route path={'login'} element={<LoginView/>}/>
                </Route>
            </Routes>
        </Router>
    </div>
  );
}

export default App;
