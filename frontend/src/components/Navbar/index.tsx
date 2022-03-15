import {ReactComponent as GithubIcon} from 'assets/img/github.svg';
import './styles.css'

function Navbar(){
    return(
        <header>
        <nav className="container">
            <div className="supersim-nav-content">
                <h1>SuperSim</h1>
                <a href="https://github.com/guilhermesci/supersim" target="_blank" rel="noreferrer">
                    <div className="supersim-contact-container">
                        <GithubIcon />
                        <p className="supersim-contact-link">/guilhermesci</p>
                    </div>
                </a>
            </div>
        </nav>
    </header>
    );
}

export default Navbar;
