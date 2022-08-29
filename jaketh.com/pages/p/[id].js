import { useRouter } from 'next/router';
import Layout from '../../components/Layout';
import Link from 'next/link';
import getAllProjects from '../../components/GetProjects'

const ProjectPost = ({post}) => (
    <div id="outest">
      <Link href="/projects/[title]" as={`/projects/${post.title}` } >
        <a>
          <div id="container-outer">
            <div id="image" style={{backgroundImage: "url(" + post.image + ")"}}>
              <div id="inner-filter"></div>
              <h1 id="title">{post.title}</h1>
              <p id="post-description">{post.description}</p>
            </div>
            
          </div>
        </a>
      </Link>
      <style jsx>{`
      #outest {
        width: 33.33%;
        width: calc(100%/3);
        height: 33vw;
        min-height: 262px;
      }

      #post-description {
        font-family: 'Roboto', 'Arial';
        color: white;
        z-index: 0;
        position: absolute;
        bottom: 2%;
        text-align: center;

        margin-left: 20%;;
        width: 60%;
        font-size: 1em;
      }

      #title {
        font-size: 3em;
        font-family: 'Roboto', 'Arial';
        color: white;
        z-index: 0;
        position: absolute;
        bottom: 42%;
        text-align: center;
        margin-left: 3%;;
        width: 94%;
      }

      #container-outer {
        width: 100%;
        height: 100%;
        position: relative;
      }

      #image {
        background-attachment: fixed;
        background-position: right;
        background-repeat: no-repeat;
        background-size: cover;

        width: 100%;
        height: 100%;
        z-index: -1;

        background-color: rgba(71, 84, 132, 0.49);

        text-align: center;
      }

      #inner-filter {
        z-index: 0;
        color: white;
        width: 100%;
        height: 100%;
        position: relative;
        top: 0;
        background-color: rgba(71, 84, 132, 0.49);
    }

    a {
      text-decoration: none;
    }

    a:hover #inner-filter {
      background-color: rgba(41, 54, 102, 0.49);
    }
      `}</style>
    </div>
);

const ProjectGuide = () => {

    var projects = getAllProjects();


    const router = useRouter();
    const { id } = router.query;
    projects = projects.filter(p => (p.tags.indexOf(id) > -1));

    var pageDescription;

    switch (id) {
        case "projects":
          pageDescription = {
            title: "All Projects",
            description: "A comprehensive list of all projects by Jake Hoffman"
          }
          break;
        case "software-development":
            pageDescription = {
              title: "Software Based Projects",
              description: "Software developed by Jake Hoffman"
            }
          break;
        case "web-development":
            pageDescription = {
              title: "Web Based Projects",
              description: "Web based projects by Jake Hoffman"
            }
          break;
        case "art-design":
            pageDescription = {
              title: "Illustration and Logo Design Projects",
              description: "Creative projects by Jake Hoffman"
            }
          break;
        default: 
        pageDescription = {
          title: "All Projects",
          description: "List of all projects by Jake Hoffman"
        }
        break;
    }

  return (
    <Layout>
      <div>
        <h1 id="title">{pageDescription.title}</h1>
        <p id="description">{pageDescription.description}</p>

        <div id="container">
          {projects.map(post => (
            <ProjectPost key={post.title} post={post} />
            ))}
        </div>
        
      </div>
      <style jsx>{`
      #title {
        color: white;
        font-size: 2em;
        font-family: 'Roboto', sans-serif;

        padding-left: 32px;
        padding-top: 32px;
      }

      #description {
        color: white;
        font-size: 1em;
        font-family: 'Roboto', sans-serif;
        padding-left: 32px;
      }

      #container {
        padding-top: 16px;
        padding-bottom: 10vh;
        width: 100%;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
      }

      #container>* {
        flex: 1 1 160px;
    }
      `}</style>
    </Layout>
  );
};

ProjectGuide.getInitialProps = async () => {
  return {};
}

export default ProjectGuide;