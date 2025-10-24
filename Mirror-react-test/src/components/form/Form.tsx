import { useState } from 'react'
import './index.css'

export function Form() {
    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault()

        const user = {
            name,
            email,
            password,
        }

        try {
            const response = await fetch('http://localhost:8080/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user),
            })

            if (response.ok) {
                const newUser = await response.json()
                console.log('Usuário criado com sucesso:', newUser)
                alert('Usuário cadastrado com sucesso!')
                setName('')
                setEmail('')
                setPassword('')
            } else {
                const errorData = await response.json()
                console.error('Erro ao criar usuário:', errorData)
                alert(`Erro ao cadastrar: ${errorData.message || 'Tente novamente.'}`)
            }
        } catch (error) {
            console.error('Falha na requisição:', error)
            alert('Não foi possível conectar ao servidor. Verifique se ele está rodando.')
        }
    }

    return (
        <div className='box'>
            <div className='content'>
                <form className='form' onSubmit={handleSubmit}>
                    <h2 className='cadastrar__title'>CADASTRAR</h2>
                    <h3 className='title__form'>NOME</h3>
                    <input
                        type='text'
                        placeholder='NOME'
                        className='input__name'
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                    <h3 className='title__form'>EMAIL</h3>
                    <input
                        type='email'
                        placeholder='EMAIL'
                        className='input__email'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <h3 className='title__form'>SENHA</h3>
                    <input
                        type='password'
                        placeholder='SENHA'
                        className='input__senha'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <button type='submit' className='btn__cadastrar'>CADASTRAR</button>
                </form>
            </div>
        </div>
    )
}

export default Form