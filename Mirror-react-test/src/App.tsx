import { useState } from 'react'
import Register from './pages/Register/Register'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Register></Register>
    </>
  )
}

export default App
