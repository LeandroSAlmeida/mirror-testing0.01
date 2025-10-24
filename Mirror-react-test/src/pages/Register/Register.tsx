import React from 'react'
import Navbar from '../../components/navbar/Navbar'
import './index.css'
import Form from '../../components/form/Form'


export function Register() {
    return (
        <div>
            <Navbar></Navbar>
            <section className='register'>
                <div className='register__content'>
                    <div className='register__logo'>
                        <img
                            src='/src/assets/logo.jpg'
                            alt='Logo'
                            className='logo__image'
                        />
                    </div>
                    <div className='register__form'>
                        <Form />
                    </div>
                </div>
            </section>
        </div>
    )
}

export default Register
