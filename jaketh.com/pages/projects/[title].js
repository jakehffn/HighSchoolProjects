import { useRouter } from 'next/router';
import Layout from '../../components/Layout';
import Link from 'next/link';
import getAllProjects from '../../components/GetProjects'


export default function Projects() {

  var projects = getAllProjects();

  const router = useRouter();
  const { title } = router.query;

  projects = projects.filter(p => p.title === title);
  console.log(projects);

  var project = projects[0];

  return(
    <Layout>
      <div>
        <h1 id="title">{title}</h1>
        {/* {project.page} */}
      </div>
      <style jsx>{`
      #title {
        color: white;
        font-size: 2em;
        font-family: 'Roboto', sans-serif;

        padding: 32px;
      }
      `}</style>
    </Layout>
    
  )
}