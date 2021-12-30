import './App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import LoginView from "./views/LoginView";
import RegistrationView from "./views/RegistrationView";
import DashBoard from "./views/DashBoard";
import CreateHealthCenterView from "./views/CreateHealthCenterView";
import CreateLegislationView from "./views/CreateLegislationView";
import LegislationDashBoard from "./views/LegislationDashBoard";
import CreateStateRepView from "./views/CreateStateRepView";
import StateRepDashboard from "./views/StateRepDashboard";
import StateSenatorDashBoardView from "./views/StateSenatorDashBoardView";
import CreateFederalRepView from "./views/CreateFederalRepView";
import FederalRepView from "./views/FederalRepView";
import CongressionalDashboard from "./views/CongressionalDashboard";
import USSenatorsDashboard from "./views/USSenatorsDashboard";
import USSenatorOffice from "./views/USSenatorOffice";

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
                        <Route path={'addFederalRep'}>
                            <Route path={':type'} element={<CreateFederalRepView/>}/>
                        </Route>
                        <Route path={'federalRepFinish'} element={<FederalRepView/>}/>
                        <Route path ={'stateSenatorDashboard'} element={<StateSenatorDashBoardView/>}/>
                        <Route path={'senatorDashboard'} element={<USSenatorsDashboard/>}/>
                    </Route>
                    <Route path={'location'}>
                        <Route path={'senator/:id'} element={<USSenatorOffice/>}/>
                    </Route>
                </Route>
            </Routes>
        </Router>
    </div>
  );
}

export default App;
