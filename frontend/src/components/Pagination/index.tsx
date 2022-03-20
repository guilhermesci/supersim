import {ReactComponent as Arrow} from 'assets/img/arrow.svg';
import { ClientPage } from 'types/client';
import './styles.css';

type Props = {
    page: ClientPage;
    onChange: Function;
}

function Pagination( { page, onChange } : Props) {
    return(
        <div className="supersim-pagination-container">
            <div className="supersim-pagination-box">
                <button className="supersim-pagination-button" disabled={page.first} onClick={() => onChange(page.number - 1)}>
                    <Arrow />
                </button>
                <p>{`${page.number + 1} de ${page.totalPages}`}</p>
                <button className="supersim-pagination-button" disabled={page.last} onClick={() => onChange(page.number + 1)}>
                    <Arrow className="supersim-flip-horizontal" />
                </button>
            </div>
        </div>
    );
}

export default Pagination;