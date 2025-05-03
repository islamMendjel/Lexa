const fs=require('fs'); // file system (read and write on files)

// Tokens definitions for each type
const types=
{
  Identificateur : /^[a-zA-Z][a-zA-Z0-9]*$/,
  ConstanteEntière : /^\d+$/,
  ConstanteRéelle : /^\d+(\.\d+)?(e[+-]?\d+)?$/i,
  ConstanteChaine : /^['"].*['"]$/,
  OperateurArithmétique : /^[+\-*/]$/,
  OperateurLogique : /^(>|>=|<|<=|=|<>)$/,
  Keywords : /^(program|const|var|begin|end|integer|reel|char|string|record|if|then|else|endif|Reapet|until)$/,
  Symbols : /^[;,:().]$/, 
  AssignmentOperator : /^:=$/
};

function getType(word)
{  
  if(types.Keywords.test(word))
  {
    return 'Keywords';
  }
  
  for (const [type,regex] of Object.entries(types))
  {
    if (regex.test(word))
    {
      return type;
    }
  }
}

function tokenize(line)
{
  const tokenRegex = /(\d+(\.\d+e[+-]?\d+|e[+-]?\d+)?|['"].*?['"]|[+\-*/=<>!]=?|:=|[;,:().]|\b[a-zA-Z][a-zA-Z0-9]*\b)/g;
  return line.match(tokenRegex) || [];
}


fs.readFile('test.txt','utf8',(error,data) =>
  {
    if (error)
    {
      console.error('Error reading file:',error);
      return;
    }

    const lines = data.split(/\r?\n/); // Split by lines
    const results = lines.flatMap(line =>
    {
      const tokens = tokenize(line); // Tokenize each line
      return tokens.map(token => `Type : ${getType(token)}   Value : ${token}`);
    }).join('\n');


  fs.writeFile('jetons.txt',results,'utf8',error =>
    {
    if(error)
      {
        console.error('Error writing file:',error);
        return;
      }
    console.log('Jetons.txt has been created.');
  });
});