import React, {Component} from 'react';
import {Table} from 'reactstrap';
import {Button} from 'reactstrap'

class App extends Component {
    render() {
        return (
            <div className="App container">
                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>BirthDate</th>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>Actions</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>09.09.2009</td>
                        <td>Almaz</td>
                        <td>FEMALE</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2">Edit</Button>
                            <Button color="danger" size="sm">Delete</Button>
                        </td>
                    </tr>

                    <tr>
                        <td>2</td>
                        <td>04.04.1996</td>
                        <td>Aleksandr</td>
                        <td>MALE</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2">Edit</Button>
                            <Button color="danger" size="sm">Delete</Button>
                        </td>
                    </tr>

                    <tr>
                        <td>3</td>
                        <td>01.01.2020</td>
                        <td>Yulia</td>
                        <td>FEMALE</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2">Edit</Button>
                            <Button color="danger" size="sm">Delete</Button>
                        </td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default App;
