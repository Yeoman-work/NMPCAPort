import './App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import LoginView from "./views/LoginView";
import RegistrationView from "./views/RegistrationView";
import DashBoard from "./views/DashBoard";
import CreateHealthCenterView from "./views/CreateHealthCenterView";

function App() {
  return (
    <div className="App">
        <Router>
            <Routes>
                <Route path={'/yeoman'}>
                    <Route path={'register'} element={<RegistrationView/>}/>
                    <Route path={'login'} element={<LoginView/>}/>
                    <Route path={'dashboard'} element={<DashBoard/>}/>
                    <Route path={'healthCenters'}>
                        <Route path={'addHealthCenter'} element={<CreateHealthCenterView/>}/>
                    </Route>
                </Route>
            </Routes>
        </Router>
    </div>
  );
}

export default App;
