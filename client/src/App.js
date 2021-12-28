import './App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import LoginView from "./views/LoginView";
import RegistrationView from "./views/RegistrationView";
import DashBoard from "./views/DashBoard";
import CreateHealthCenterView from "./views/CreateHealthCenterView";
import CreateLegislationView from "./views/CreateLegislationView";
import LegislationDashBoard from "./views/LegislationDashBoard";
import CreateStateRepView from "./views/CreateStateRepView";
import StatePoliticianElement from "./components/StatePoliticianElement";
import StateRepDashboard from "./views/StateRepDashboard";
import StateSenatorDashBoardView from "./views/StateSenatorDashBoardView";
import CreateFederalRepView from "./views/CreateFederalRepView";
import FederalRepView from "./views/FederalRepView";
import CongressionalDashboard from "./views/CongressionalDashboard";

function App() {
  return (
    <div className="App container-fluid">
        <Router>
            <Routes>
                <Route path={'/yeoman'}>
                    <Route path={'register'} element={<RegistrationView/>}/>
                    <Route path={'login'} element={<LoginView/>}/>
                    <Route path={'dashboard'} element={<DashBoard/>}/>
                    <Route path={'healthCenters'}>
                        <Route path={'addHealthCenter'} element={<CreateHealthCenterView/>}/>
                    </Route>
                    <Route path={'legislation'}>
                        <Route path={'dashboard'} element={<LegislationDashBoard/>}/>
                        <Route path={'createLegislation'} element={<CreateLegislationView/>}/>
                    </Route>
                    <Route path={'government'}>
                        <Route path={'stateRepDashboard'} element={<StateRepDashboard/>}/>
                        <Route path={'addStateRep'}>
                            <Route path={':type'} element={<CreateStateRepView/>}/>
                        </Route>
                        <Route path={'congressionalRepDashboard'} element={<CongressionalDashboard/>}/>
                        <Route path={'addFederalRep'} element={<CreateFederalRepView/>}/>
                        <Route path={'federalRepFinish'} element={<FederalRepView/>}/>
                        <Route path ={'stateSenatorDashboard'} element={<StateSenatorDashBoardView/>}/>
                    </Route>
                </Route>
            </Routes>
        </Router>
    </div>
  );
}

export default App;
