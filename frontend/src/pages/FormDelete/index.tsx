import FormCardDelete from 'components/FormCardDelete';
import { useParams } from 'react-router-dom';

function FormDelete() {

    const params = useParams();

    return (
        <FormCardDelete clientId={`${params.clientId}`}/>
    );
}

export default FormDelete;