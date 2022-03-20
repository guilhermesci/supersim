import axios from "axios";
import ClientCard from "components/ClientCard";
import Pagination from "components/Pagination";
import { useEffect } from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import { ClientPage } from "types/client";
import { BASE_URL } from "utils/requests";

function List() {

    const [pageNumber, setPageNumber] = useState(0);

    const [page, setPage] = useState<ClientPage>({
        content: [],
        last: true,
        totalPages: 0,
        totalElements: 0,
        size: 8,
        number: 0,
        first: true,
        numberOfElements: 0,
        empty: true
    });

    useEffect(() => {
        axios.get(`${BASE_URL}/clients?size=${page.size}&page=${pageNumber}&sort=name`)
            .then(response => {
                const data = response.data as ClientPage;
                setPage(data);
            });
    }, [pageNumber]);

    const handlePageChange = (newPageNumber : number) => {
        setPageNumber(newPageNumber);
    }

    return (
        <>
            <Pagination page={page} onChange={handlePageChange}/>

            <div className="container">
                <div className="row">
                    {page.content.map(client => (
                        <div key={client.id} className="col-sm-6 col-lg-4 col-xl-3 mb-3">
                            <ClientCard client={client} />
                        </div>
                    )
                    )}
                </div>
            </div>
        </>
    );
}

export default List;