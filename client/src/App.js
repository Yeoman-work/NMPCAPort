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
import USSenatorStaff from "./views/USSenatorStaff";
import CongressionalRepOffice from "./views/CongressionalRepOffice";
import CongressionalRepStaff from "./views/CongressionalRepStaff";
import CreateNetworkingGroupView from "./views/CreateNetworkingGroupView";
import NetworkingGroupDashboard from "./views/NetworkingGroupDashboard";
import CreateContactsView from "./views/CreateContactsView";
import ContactElement from "./components/ContactElement";
import ContactDashboardView from "./views/ContactDashboardView";
import CreateInterimCommittee from "./views/CreateInterimCommittee";

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
                            <Route path={'newMexico'}>
                                <Route path ={'stateSenatorDashboard'} element={<StateSenatorDashBoardView/>}/>
                                <Route path={'stateRepDashboard'} element={<StateRepDashboard/>}/>
                                <Route path={'interimCommittees'} element={<CreateInterimCommittee/>}/>
                                <Route path={'addStateRep'}>
                                    <Route path={':type'} element={<CreateStateRepView/>}/>
                                </Route>
                            </Route>
                            <Route path={'usGovernment'}>
                                <Route path={'congressionalRepDashboard'} element={<CongressionalDashboard/>}/>
                                <Route path={'federalRepFinish'} element={<FederalRepView/>}/>
                                <Route path={'senatorDashboard'} element={<USSenatorsDashboard/>}/>
                                <Route path={'addFederalRep'}>
                                    <Route path={':type'} element={<CreateFederalRepView/>}/>
                                </Route>
                            </Route>
                        </Route>
                        <Route path={'location'}>
                            <Route path={'senator/:id'} element={<USSenatorOffice/>}/>
                            <Route path={'congressionalRep/:id'} element={<CongressionalRepOffice/>}/>
                        </Route>
                        <Route path={'staff'}>
                            <Route path={'senator/:id'} element={<USSenatorStaff/>}/>
                            <Route path={'congressionalRep/:id'} element={<CongressionalRepStaff/>}/>
                        </Route>
                        <Route path={'networkingGroup'}>
                            <Route path={'dashboard'} element={<NetworkingGroupDashboard/>}/>
                            <Route path={'editGroup/:id'} element={<CreateNetworkingGroupView/>}/>
                            <Route path={'createNetworkingGroup'} element={<CreateNetworkingGroupView/>}/>
                        </Route>
                        <Route path={'contacts'}>
                            <Route path={'dashboard'} element={<ContactDashboardView/>}/>
                            <Route path={'addContact'} element={<CreateContactsView/>}/>
                        </Route>
                        <Route path={'interimGroup'}>
                            <Route path={'addCommittee'} element={<CreateInterimCommittee/>}/>
                        </Route>
                    </Route>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
