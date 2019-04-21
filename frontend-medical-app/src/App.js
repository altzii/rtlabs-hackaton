import React, {Component} from 'react';
import axios from 'axios';
import {
    Button,
    Collapse,
    FormGroup,
    Input,
    Label,
    Modal,
    ModalBody,
    ModalFooter,
    ModalHeader,
    Nav,
    Navbar,
    NavbarBrand,
    NavbarToggler,
    NavItem,
    NavLink,
    Table
} from 'reactstrap';

const GATEWAY_URL = 'http://10.77.105.117:8080';
const PATIENT_SERVICE_NAME = '/patients-service';
const PATIENT_RESOURCE_URL = GATEWAY_URL + PATIENT_SERVICE_NAME + "/patient";

class App extends Component {

    state = {
        patients: [],
        newPatientModal: false,
        newPatientData: {
            resourceType: 'Patient',
            id: '11',
            birthDate: null,
            name: [{
                given: '',
                family: ''
            }],
        },
    };

    componentWillMount() {
        axios.get(PATIENT_RESOURCE_URL)
            .then((response) => {
                this.setState({
                        patients: response.data
                    }
                )
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    toggleNewPatientModal() {
        this.setState({
            newPatientModal: !this.state.newPatientModal
        });
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    addPatient() {
        axios.post(PATIENT_RESOURCE_URL, this.state.newPatientData)
            .then((response) => {
                let {patients} = this.state;

                patients.push(response.data);

                this.setState({
                    patients: patients,
                    newPatientModal: false,
                    newPatientData: {
                        name: [{
                            given: '',
                            family: ''
                        }]
                    }

                });
            })
    }

    render() {
        let patients = this.state.patients.map((patient) => {
            return (
                <tr key={patient.id}>
                    <td>{patient.id}</td>
                    <td>{patient.birthDate}</td>
                    <td>{patient.name[0].given}</td>
                    <td>{patient.name[0].family}</td>
                    <td>{patient.gender}</td>
                    <td>
                        <Button color="success" size="sm" className="mr-2">Edit</Button>
                        <Button color="danger" size="sm">Delete</Button>
                    </td>
                </tr>
            )
        });

        return (
            <div className="App container">
                <div>
                    <Navbar color="light" light expand="md">
                        <NavbarBrand href="/">RT LABS HACKATON</NavbarBrand>
                        <NavbarToggler onClick={this.toggle.bind(this)}/>
                        <Collapse navbar>
                            <Nav className="ml-auto" navbar>
                                <NavItem>
                                    <NavLink href="https://github.com/altzii/rtlabs-hackaton">GitHub</NavLink>
                                </NavItem>
                            </Nav>
                        </Collapse>
                    </Navbar>
                </div>

                <Button className="my-3" color="primary" onClick={this.toggleNewPatientModal.bind(this)}>Add
                    patient</Button>

                <Modal isOpen={this.state.newPatientModal} toggle={this.toggleNewPatientModal.bind(this)}>
                    <ModalHeader toggle={this.toggleNewPatientModal.bind(this)}>Add a new patient</ModalHeader>
                    <ModalBody>
                        <FormGroup>
                            <Label for="given">First name</Label>
                            <Input id="given" value={this.state.newPatientData.name[0].given} onChange={(e) => {
                                let {newPatientData} = this.state;
                                newPatientData.name[0].given = e.target.value;
                                this.setState({newPatientData})
                            }}/>
                        </FormGroup>

                        <FormGroup>
                            <Label for="family">Surname</Label>
                            <Input id="family" value={this.state.newPatientData.name[0].family} onChange={(e) => {
                                let {newPatientData} = this.state;
                                newPatientData.name[0].family = e.target.value;
                                this.setState({newPatientData})
                            }}/>
                        </FormGroup>

                        <FormGroup>
                            <Label for="birthDate">Birthdate</Label>
                            <Input id="birthDate" type="date" value={this.state.newPatientData.birthDate} onChange={(e) => {
                                let {newPatientData} = this.state;
                                newPatientData.birthDate = e.target.value;
                                this.setState({newPatientData})
                            }}/>
                        </FormGroup>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.addPatient.bind(this)}>Add</Button>{' '}
                        <Button color="secondary" onClick={this.toggleNewPatientModal.bind(this)}>Cancel</Button>
                    </ModalFooter>
                </Modal>

                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>BirthDate</th>
                        <th>First name</th>
                        <th>Surname</th>
                        <th>Gender</th>
                        <th>Actions</th>
                    </tr>
                    </thead>

                    <tbody>
                    {patients}
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default App;
