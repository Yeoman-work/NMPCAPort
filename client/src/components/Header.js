import React from "react";
import {Link} from "react-router-dom";


const Header = props =>{


    return(
            <nav className="navbar navbar-expand-lg navbar-light bg-light ">
                <div className="container-fluid">
                    <Link className="navbar-brand" to={''}>Navbar</Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon">text</span>
                    </button>
                    <div className="collapse  navbar-collapse ms-auto me-5" id="navbarSupportedContent">
                        <ul className="navbar-nav  mb-2 mb-lg-0 ms-auto">
                            <li className="nav-item">
                                <Link className="nav-link active" aria-current="page" to={'/yeoman/dashboard'}>HealthCenters</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to={'/yeoman/legislation/dashboard'}>Legislation</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to={'/yeoman/contacts/dashboard'}>Contacts</Link>
                            </li>
                            <li className={"nav-item"}>
                                <Link className={"nav-link"} to={'/yeoman/networkingGroup/dashboard'}>Networking Groups</Link>
                            </li>
                            <li className="nav-item dropdown">
                                <Link className="nav-link dropdown-toggle" to={''} id="navbarDropdown" role="button"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    New Mexico Gov't
                                </Link>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><Link className="dropdown-item" to={'/yeoman/government/newMexico/stateRepDashboard'}>State Representatives</Link></li>
                                    <li><Link className="dropdown-item" to={'/yeoman/government/newMexico/stateSenatorDashboard'}>State Senators</Link></li>
                                    <li><Link className='dropdown-item' to={'/yeoman/government/newMexico/interimCommittees'}>Interim Committee(s)</Link></li>
                                    <li><Link className='dropdown-item' to={'/yeoman/government/newMexico/committees/house'}>House Committee(s)</Link></li>
                                    <li><Link className='dropdown-item' to={'/yeoman/government/newMexico/committees/senate'}>Senate Committee(s)</Link></li>
                                    <li>
                                        <hr className="dropdown-divider"/>
                                    </li>
                                    <li><Link className="dropdown-item" to={''}>Something else here</Link></li>
                                </ul>
                            </li>
                            <li className="nav-item dropdown">
                                <Link className="nav-link dropdown-toggle" to={''} id="navbarDropdown" role="button"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    US Government
                                </Link>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><Link className='dropdown-item' to={'/yeoman/government/usGovernment/congressionalRepDashboard'}>Congressional Representatives</Link></li>
                                    <li><Link className='dropdown-item' to={'/yeoman/government/usGovernment/senatorDashboard'}>Senators</Link></li>
                                    <li>
                                        <hr className="dropdown-divider"/>
                                    </li>
                                    <li><Link className="dropdown-item" to={''}>Something else here</Link></li>
                                </ul>
                            </li>
                            <li className="nav-item dropdown">
                                <Link className="nav-link dropdown-toggle
                                " to={''} id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">stuff</Link>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><Link className="dropdown-item" to={''}></Link></li>
                                    <li><Link className="dropdown-item" to={''}>State Senators</Link></li>
                                    <li>
                                        <hr className="dropdown-divider"/>
                                    </li>
                                    <li><Link className="dropdown-item" to={''}>Log Out</Link></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

    )
}

export default Header