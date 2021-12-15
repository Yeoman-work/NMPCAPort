import React from "react";
import {Link} from "react-router-dom";


const Header = props =>{



    return(
        <div className={'text-end'}>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid ms-auto">
                    <Link className="navbar-brand me-auto" to={'/yeoman/dashboard'} >Yeoman's Work</Link>
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>

                    <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div className="navbar-nav ms-auto">

                            <Link className="nav-link active" aria-current="page" to={'/yeoman/dashboard'}>Health Centers</Link>
                            <Link className="nav-link" to={'/'}>Contacts</Link>
                            <Link className="nav-link" to={'/'}>Government</Link>
                            <Link className="nav-link" to={'/yeoman/legislation/createLegislation'}>Legislation</Link>
                            <Link className="nav-link disabled" to={'/'} tabIndex="-1" aria-disabled="true">Disabled</Link>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    )
}

export default Header